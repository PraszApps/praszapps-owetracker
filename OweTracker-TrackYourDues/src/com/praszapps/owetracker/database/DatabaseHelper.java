package com.praszapps.owetracker.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.praszapps.owetracker.bo.Due;
import com.praszapps.owetracker.bo.Friend;

/**
 * Contains all the Database related APIs necessary to run the application
 * @author Prasannajeet Pani
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	
	// All Static variables

	// Database Version and name
	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "owetrackerDB";
	//public static final String DATABASE_NAME = Environment.getExternalStorageDirectory()+"/owetrackerDB";
	
	//Table Names
	public static final String TABLE_FRIEND = "friend";
	public static final String TABLE_DUE = "friend_due";
	
	//Friend Column Names
	public static final String FRIEND_COLUMN_ID = "friend_id";
	public static final String FRIEND_COLUMN_NAME = "friend_name";
	public static final String FRIEND_COLUMN_CURRENCY = "currency";
	public static final String FRIEND_COLUMN_DUE = "total_amt_due";
	
	public static final String SQL_DROP_TABLE_FRIEND = "DROP TABLE IF EXISTS friend";
	public static final  String SQL_CREATE_TABLE_FRIEND = "CREATE TABLE IF NOT EXISTS "+TABLE_FRIEND
 			+ "("
 			+ "friend_id				VARCHAR(50) PRIMARY KEY NOT NULL,"
 			+ "friend_name				VARCHAR(75),"
 			+ "currency                 VARCHAR(5),"
 			+ "total_amt_due 			INT"
 			+");";
	// Due column names
	public static final String SQL_DROP_TABLE_DUE = "DROP TABLE IF EXISTS friend_due";
	public static final String DUE_COLUMN_ID = "due_id";
	public static final String DUE_COLUMN_FRIEND_ID = "friend_id";
	public static final String DUE_COLUMN_DATE = "date";
	public static final String DUE_COLUMN_AMOUNT = "amount";
	public static final String DUE_COLUMN_REASON = "reason";
	public static final String SQL_CREATE_TABLE_DUE = "CREATE TABLE IF NOT EXISTS "+TABLE_DUE
 			+ "("
 			+ "due_id					VARCHAR(50) PRIMARY KEY,"
 			+ "friend_id				VARCHAR(50),"
 			+ "date						INT,"
 			+ "amount		 			INT,"
 			+ "reason                   VARCHAR(100)"
 			+");";
	
	/**
	 * 
	 * Adds a new Friend Data into the database
	 * @param friend - object of the Friend class to be added to the database
	 * @param db - Database instance
	 * @return True if success, false if failure
	 */
	public static Boolean createFriendRecord(Friend friend, SQLiteDatabase db) {
		String id = friend.getId();
		String name = friend.getName();
		String currency = friend.getCurrency();
		int totalAmtDue = 0;
		ContentValues values = new ContentValues();
		values.put(FRIEND_COLUMN_ID, id);
		values.put(FRIEND_COLUMN_NAME, name);
		values.put(FRIEND_COLUMN_CURRENCY, currency);
		values.put(FRIEND_COLUMN_DUE, totalAmtDue);
		if(db.insert(TABLE_FRIEND, null, values) != -1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	/**
	 * Used to retrieve details of all the friends that are stored in the database, sorted by their names
	 * @param db - instance of the database
	 * @return ArrayList of Friend type containing details of all friends
	 */
	public static ArrayList<Friend> getAllFriends(SQLiteDatabase db) {
		ArrayList<Friend> friendList = new ArrayList<Friend>();
		// Select All Query
		String selectQuery = "SELECT * FROM friend ORDER BY friend_name";

		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Friend friend = new Friend();
				friend.setId(cursor.getString(0));
				friend.setName(cursor.getString(1));
				friend.setCurrency(cursor.getString(2));
				friend.setOweAmount(Integer.parseInt(cursor.getString(3)));
				// Adding friend to list
				friendList.add(friend);
			} while (cursor.moveToNext());
		}
		if(!cursor.isClosed()) {
			cursor.close();
		}

		// return contact list
		return friendList;
		
	}
	
	/**
	 * Method to update the friend details
	 * @param friend - The friend object to be updated
	 * @param db - Database instance
	 * @return - true if success, false if not
	 */
	public static Boolean updateFriend(Friend friend, SQLiteDatabase db) {
		String sql = String.format("SELECT friend_id FROM friend WHERE friend_id = '%s';", friend.getId());
		Cursor c = db.rawQuery(sql, null);
		
		// Checking if the uuid exists
		if(c.getCount() > 0) {
			c.moveToFirst();
			ContentValues values = new ContentValues();
			values.put(FRIEND_COLUMN_NAME, friend.getName());
			values.put(FRIEND_COLUMN_CURRENCY, friend.getCurrency());
			String whereClause = "friend_id='"+friend.getId()+"'";
			if(!c.isClosed()) {
				c.close();
			}
			if(db.update(TABLE_FRIEND, values, whereClause, null) > 0) {
				return true;
			} else {
				return false;
			}
			
		} else {
			if(!c.isClosed()) {
				c.close();
			}
			return false;
		}
		
	}
	
	public static void updateFriendDue(String friendId, SQLiteDatabase db) {
		int due = 0;
		Cursor c;
		String sql;
		
		sql = String.format("SELECT SUM("+DUE_COLUMN_AMOUNT+") AS TOTAL_DUE FROM "+
		TABLE_DUE+" WHERE "+DUE_COLUMN_FRIEND_ID+" = '%s'", friendId);
		c = db.rawQuery(sql, null);
		if(c.getCount() > 0) {
			c.moveToFirst();
			due = c.getInt(c.getColumnIndex("TOTAL_DUE"));
		}
		//Logic to update the total due in friend's database
		sql = String.format("SELECT friend_name FROM friend WHERE friend_id = '%s' ;", friendId);
		c = db.rawQuery(sql, null);
		if(c.getCount() > 0) {
			c.moveToFirst();
			sql = String.format("UPDATE friend SET " + "total_amt_due = '%d'" + 
			" WHERE friend_id = '%s' ;", due, friendId);
			dbExecSQL(db, sql);
		}
		
		if(!c.isClosed()) {
			c.close();
		}
	}
		
	public static Boolean deleteFriendRecord(String friendId, SQLiteDatabase db) {
		String sql = String.format("SELECT friend_name FROM friend WHERE friend_id = '%s' ;", friendId);
		Cursor c = db.rawQuery(sql, null);
		if(c.getCount() > 0) {
			c.moveToFirst();
			String whereClause = "friend_id='"+friendId+"'";
			db.delete(TABLE_FRIEND, whereClause, null);
			db.delete(TABLE_DUE, whereClause, null);
			if(!c.isClosed()) {
				c.close();
			}
			return true;
		} else {
			if(!c.isClosed()) {
				c.close();
			}
			return false;
		}
	}
		
	/**
	 * Get the total number of friends with dues
	 * @param db - Database instance
	 * @return Total number of friends with dues
	 */
	public static int getFriendsWithDuesCount(SQLiteDatabase db) {
		String sql = "SELECT * FROM "+TABLE_FRIEND+" WHERE "+FRIEND_COLUMN_DUE+" != 0;";
		Cursor c = db.rawQuery(sql, null);
		int count = c.getCount();
		if(!c.isClosed()) {
			c.close();
		}
		return count;
	}
	
	/**
	 * 
	 * Returns the currency symbol of the friend
	 * @param friendId - Unique ID of the friend whose currency is sought
	 * @param db - Database instance
	 * @return Currency symbol
	 */
	public static String getCurrency(String friendId, SQLiteDatabase db) {
		String sql = "SELECT currency FROM "+TABLE_FRIEND+" WHERE "+FRIEND_COLUMN_ID+" = '"+friendId+"';";
		String currency = null;
		Cursor c = db.rawQuery(sql, null);
		
		if(c.getCount()  == 1) {
			c.moveToFirst();
			currency = c.getString(c.getColumnIndex(FRIEND_COLUMN_CURRENCY));
		}
		
		return currency;
	}
	
	public static Friend getFriendData(String friendId, SQLiteDatabase db) {
		String sql = String.format("SELECT * FROM friend WHERE friend_id = '%s'", friendId);
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.getCount() == 1) {
			cursor.moveToFirst();
			Friend friend = new Friend();
			friend.setId(cursor.getString(0));
			friend.setName(cursor.getString(1));
			friend.setCurrency(cursor.getString(2));
			friend.setOweAmount(Integer.parseInt(cursor.getString(3)));
			if(!cursor.isClosed()) {
				cursor.close();
			}
			return friend;
		} else {
			return null;
		}
	}
	
		
	public static Boolean deleteDueData(String dueId, SQLiteDatabase db) {
		String sql = String.format("SELECT "+DUE_COLUMN_ID+","+DUE_COLUMN_FRIEND_ID+" " +
				"FROM "+TABLE_DUE+" WHERE "+DUE_COLUMN_ID+" = '"+dueId+"';");
		Cursor c = db.rawQuery(sql, null);
		if(c.getCount() == 1) {
			c.moveToFirst();
			String whereClause = "due_id='"+dueId+"'";
			db.delete(TABLE_DUE, whereClause, null);
			updateFriendDue(c.getString(c.getColumnIndex(DUE_COLUMN_FRIEND_ID)), db);
			if(!c.isClosed()) {
				c.close();
			}
			return true;
		} else {
			if(!c.isClosed()) {
				c.close();
			}
			return false;
		}
	}
	
	public static Boolean deleteAllFriendDues(String friendId, SQLiteDatabase db) {
		String sql = String.format("SELECT due_id FROM friend_due WHERE friend_id = '%s' ;", friendId);
		Cursor c = db.rawQuery(sql, null);
		if(c.getCount() > 0) {
			c.moveToFirst();
			String whereClause = "friend_id='"+friendId+"'";
			db.delete(TABLE_DUE, whereClause, null);
			updateFriendDue(friendId, db);
			if(!c.isClosed()) {
				c.close();
			}
			return true;
		} else {
			if(!c.isClosed()) {
				c.close();
			}
			return false;
		}
	}
	
	
	/**
	 * Creates a new due record in the database
	 * @param dueToAdd - Due object to insert into the database
	 * @param db - Database instance
	 * @return true if sucess, false if failure
	 */
	public static Boolean addDue(Due dueToAdd, SQLiteDatabase db) {
		String dueId = dueToAdd.getDueId();
		String friendId = dueToAdd.getFriendId();
		long date = dueToAdd.getDate();
		int dueAmt = dueToAdd.getAmount();
		String dueReason = dueToAdd.getReason();
		ContentValues values = new ContentValues();
		values.put(DUE_COLUMN_ID, dueId);
		values.put(DUE_COLUMN_FRIEND_ID, friendId);
		values.put(DUE_COLUMN_DATE, date);
		values.put(DUE_COLUMN_AMOUNT, dueAmt);
		values.put(DUE_COLUMN_REASON, dueReason);
		if(db.insert(TABLE_DUE, null, values) != -1) {
			updateFriendDue(friendId, db);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Method updates a due of the friend
	 * @param dueToUpdate - Due object to update
	 * @param db - Database instance
	 * @return - true if updation is successful, false if not
	 */
	public static Boolean updateDue(Due dueToUpdate, SQLiteDatabase db) {
		String sql = String.format("SELECT friend_id FROM "+ TABLE_DUE +
				" WHERE "+DUE_COLUMN_ID+" = '%s' ;", dueToUpdate.getDueId());
		Cursor c = db.rawQuery(sql, null);
		if(c.getCount() == 1) {
			c.moveToFirst();
			ContentValues values = new ContentValues();
			values.put(DUE_COLUMN_DATE, dueToUpdate.getDate());
			values.put(DUE_COLUMN_AMOUNT, dueToUpdate.getAmount());
			values.put(DUE_COLUMN_REASON, dueToUpdate.getReason());
			String whereClause = DUE_COLUMN_ID+" = '"+dueToUpdate.getDueId()+"'";
			if(!c.isClosed()) {
				c.close();
			}
			if(db.update(TABLE_DUE, values, whereClause, null) > 0) {
				updateFriendDue(dueToUpdate.getFriendId(), db);
				return true;
			} else {
				return false;
			}
			
		} else {
			if(!c.isClosed()) {
				c.close();
			}
			return false;
		}

	}

	/**
	 * Method to retrieve complete list of dues for a given friend
	 * @param friendId - The friendID of the friend
	 * @param db - Database instance
	 * @return - ArrayList of Due type, the list of Dues for the given friend
	 */
	public static ArrayList<Due> getFriendDueList(String friendId, SQLiteDatabase db) {
		ArrayList<Due> dueList = new ArrayList<Due>();
		// Select All Query
		String selectQuery = "SELECT * FROM "+TABLE_DUE+" WHERE "+FRIEND_COLUMN_ID+" = '"+friendId
				+"' ORDER BY "+DUE_COLUMN_DATE+" DESC;";

		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Due due = new Due();
				due.setDueId(cursor.getString(cursor.getColumnIndex(DUE_COLUMN_ID)));
				due.setFriendId(cursor.getString(cursor.getColumnIndex(DUE_COLUMN_FRIEND_ID)));
				due.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndex(DUE_COLUMN_DATE))));
				due.setAmount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DUE_COLUMN_AMOUNT))));
				due.setReason(cursor.getString(cursor.getColumnIndex(DUE_COLUMN_REASON)));
				// Adding friend to list
				dueList.add(due);
			} while (cursor.moveToNext());
		}
		if(!cursor.isClosed()) {
			cursor.close();
		}

		// return contact list
		return dueList;
		
	} 
	
	
	
	/**
	 * @param db
	 *            Database instance
	 * @param sql
	 *            SQL statement
	 */
	public static void dbExecSQL(SQLiteDatabase db, String sql) {
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE_FRIEND);
		db.execSQL(SQL_CREATE_TABLE_DUE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion > oldVersion) {
			db.execSQL(SQL_DROP_TABLE_DUE);
			db.execSQL(SQL_DROP_TABLE_FRIEND);
			db.execSQL(SQL_CREATE_TABLE_DUE);
			db.execSQL(SQL_CREATE_TABLE_FRIEND);
		}
	}

}
