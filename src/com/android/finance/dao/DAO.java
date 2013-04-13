package com.android.finance.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.android.finance.R;
import com.android.finance.model.Category;
import com.android.finance.model.Shopping;
import com.android.finance.util.CustomSQLCreate;

/**
 * @author Glaydston Veloso
 * @email glaydston.oliveira@gmail.com
 * @since 2013
 * @version 1.0
 */
public class DAO {
	private Context context;
	private SQLiteDatabase database;
	private CustomSQLiteOpenHelper sqLiteOpenHelper;

	public DAO(Context context) {
		sqLiteOpenHelper = new CustomSQLiteOpenHelper(context);
	}

	public void open() {
		database = sqLiteOpenHelper.getWritableDatabase();
	}

	public void close() {
		sqLiteOpenHelper.close();
	}

	public String createCategory(Category category) {
		String description = category.getDescription();

		ContentValues values = new ContentValues();
		values.put(CustomSQLCreate.CATEGORY_COLUNM_DESCRIPTION, description);
		long result = database.insert(CustomSQLCreate.TABLES_CATEGORY, null,
				values);
		return (result == -1) ? context.getString(R.string.error_create,
				description) : context.getString(R.string.success_create,
				description);
	}

	public String deleteCategory(Category category) {
		long result = 0;
		Long id = category.getId();
		String description = category.getDescription();
		try {
			result = database.delete(CustomSQLCreate.TABLES_CATEGORY,
					CustomSQLCreate.CATEGORY_COLUNM_ID + " = " + id, null);

		} catch (SQLiteException e) {
			Log.e("FashionDao", "Erro no SQLite: ", e);
		}
		return (result == -1) ? context.getString(R.string.error_create,
				description) : context.getString(R.string.success_create,
				description);
	}

	public List<Category> getAllCategory() {
		List<Category> categories = new ArrayList<Category>();
		String[] colunms = { CustomSQLCreate.CATEGORY_COLUNM_ID,
				CustomSQLCreate.CATEGORY_COLUNM_DESCRIPTION };
		Cursor cursor = database.query(CustomSQLCreate.TABLES_CATEGORY,
				colunms, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Category category = new Category();
			category.setId(cursor.getLong(0));
			category.setDescription(cursor.getString(1));

			categories.add(category);
		}
		cursor.close();
		return categories;
	}

	public String createClothes(String shopping) {
		return null;
	}

	public String deleteClothes(Shopping shopping) {
		return null;
	}

}
