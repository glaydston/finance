package com.android.finance.util;


/**
 * @author Glaydston Veloso
 * @email glaydston.oliveira@gmail.com
 * @since 2013
 * @version 1.0
 */
public class CustomSQLCreate {
	private static StringBuilder builder;

	// Database creation sql Shopping statement
	public final static String TABLES_SHOPPING = "shopping";
	public final static String SHOPPING_COLUNM_ID = "_id";
	public final static String SHOPPING_COLUNM_DESCRIPTION = "description";
	public final static String SHOPPING_COLUNM_CATEGORY = "category";
	public final static String SHOPPING_COLUNM_TYPE = "type";
	public final static String SHOPPING_COLUNM_VALUE = "value";
	public final static String SHOPPING_COLUNM_DATE = "date";

	// Database creation sql Category statement
	public final static String TABLES_CATEGORY = "category";
	public final static String CATEGORY_COLUNM_ID = "_id";
	public final static String CATEGORY_COLUNM_DESCRIPTION = "description";

	/**
	 * Este metodo cria os sqls de upgrade de tabelas
	 * 
	 * @return string
	 */
	public static String upgrade() {
		builder = new StringBuilder();
		builder.append("DROP TABLE IF EXISTS " + TABLES_CATEGORY + ";");
		builder.append("DROP TABLE IF EXISTS " + TABLES_SHOPPING + ";");
		return builder.toString();
	}

	/**
	 * Este metodo cria os sqls de criacao de tabelas
	 * 
	 * @return string
	 */
	public static String create() {
		builder = new StringBuilder();

		builder.append("CREATE TABLE " + TABLES_CATEGORY + "(");
		builder.append(CATEGORY_COLUNM_ID + " INTEGER AUTO_INCREMENT,");
		builder.append(CATEGORY_COLUNM_DESCRIPTION + " TEXT NOT NULL,");
		builder.append("CONSTRAINT PK_" + TABLES_CATEGORY + " PRIMARY KEY("
				+ CATEGORY_COLUNM_ID + "));");

		
		builder.append("\n");

		builder.append("CREATE TABLE " + TABLES_SHOPPING + "(");
		builder.append(SHOPPING_COLUNM_ID + " INTEGER AUTO_INCREMENT,");
		builder.append(SHOPPING_COLUNM_DESCRIPTION + " TEXT NOT NULL,");
		builder.append(SHOPPING_COLUNM_TYPE + " CHAR NOT NULL,");
		builder.append(SHOPPING_COLUNM_CATEGORY + " INTEGER,");
		builder.append(SHOPPING_COLUNM_VALUE + " DOUBLE,");
		builder.append(SHOPPING_COLUNM_DATE + " DATETIME,");
		builder.append(" CONSTRAINT PK_" + TABLES_SHOPPING + " PRIMARY KEY("
				+ SHOPPING_COLUNM_ID + "),");
		builder.append("CONSTRAINT FK_" + TABLES_SHOPPING + "_" + TABLES_CATEGORY
				+ " FOREIGN KEY(" + TABLES_CATEGORY + ") REFERENCES category("
				+ CATEGORY_COLUNM_ID + ");");


		return builder.toString();
	}
}
