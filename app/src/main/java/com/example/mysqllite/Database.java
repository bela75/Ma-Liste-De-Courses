package com.example.mysqllite;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

public
class Database extends SQLiteOpenHelper {


    private static final String name = "Base_DB_Users.db";
    private static final int version = 1;
    private static final Object Student = null;
    String MyNameTable = "MyNameTable";
    Cursor mCursor;
    SQLiteDatabase db;

    public
    Database ( Context context ) {
        super ( context, name, null, version );
    }

    @Override
    public
    void onCreate ( SQLiteDatabase db ) {

        String maTable = "CREATE TABLE " + MyNameTable+"("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "ColumName TEXT NOT NULL)";
        db.execSQL ( maTable );
    }

    @Override
    public
    void onUpgrade ( SQLiteDatabase db, int oldVersion, int newVersion ) {

    }

    public
    String selectNameInTable () {
        //List itemIds = new ArrayList <> ( );
        String mySQL = "select * from MyNameTable";
        Cursor cursor = getReadableDatabase().rawQuery ( mySQL, null );
        cursor.moveToFirst ();
        String names = cursor.getString ( 1 );
        cursor.close ();
        return names;
    }

    public
    List ListeNameInTable () {

        // Créer une liste
        List ListeTable = new ArrayList <> ( );
        //Lancer une requete sql sur la table MyNameTable
        String mySQL = "select * from MyNameTable";
        //Recuperer les données de la requette sql
        Cursor cursor = getReadableDatabase().rawQuery ( mySQL, null );
        //aller a la premiere ligne
        cursor.moveToFirst ();
        String myArticle = cursor.getString ( 0 );
        //String liste = cursor.getColumnName ( 1 );

        //Lancer une boucle
        while (cursor.moveToNext ()){
            ListeTable.add(myArticle);
            Log.i("MyListe", "MyListe" + ListeTable);
        }
        cursor.close ();
        return ListeTable;
    }


    /*
    *
    * Insert data in table
    * */

    public void insertData(String addArticles){
        db = this.getWritableDatabase();
        db.execSQL ( "Insert into MyNameTable (ColumName) values('"+addArticles+"')");
        db.close ();
    }

    /*
    Update
     */

    public void updateArticcles(String updateArticlers, String myArticleSelected){
        db = this.getWritableDatabase();
        db.execSQL ( "UPDATE MyNameTable SET ColumName = '"+updateArticlers+"' where ColumName = '"+myArticleSelected+"'");
        db.close ();
    }
    /*
    Read data in data base
     */

    public
    List ListeNameCursor () {
        List ListeTable = new ArrayList <> ( );
        String myQuery = "select * from MyNameTable";
        mCursor = (Cursor) this.getReadableDatabase ( ).rawQuery ( myQuery, null );

        try {
            mCursor.moveToFirst ( );
            //String myArticle = mCursor.getString ( 1 );
            String myArticle = mCursor.getString(mCursor.getColumnIndex("ColumName"));
            Log.i ( "myArticle", "Nombre d'enregistrement : == " + mCursor.getCount () );

            for (int i = 0; i < mCursor.getCount (); i++) {
                Log.i ( "myArticle", "Mes articles dans la ma liste : == " + myArticle );
                ListeTable.add ( myArticle );
                mCursor.moveToNext ();
                //myArticle = mCursor.getString ( 1 );
                myArticle = mCursor.getString(mCursor.getColumnIndex("ColumName"));
                if (myArticle.isEmpty () == true)
                {
                    Log.i ( "End", "Fin");
                    break;
                }
            }
        }
            catch(Exception e )
            {
                Log.e ( "ErrorSQL", "Error select name in table >>> " + e );
            }
            mCursor.close ( );
            return ListeTable;
        }


        /*
        Delete item
         */
        public void DeleteArticlesd(String articleDelete){
            String myQuery = "DELETE from MyNameTable WHERE ColumName = '"+articleDelete+"'";
            db = this.getWritableDatabase();
            db.execSQL ( myQuery );
            db.close ();

        }
}