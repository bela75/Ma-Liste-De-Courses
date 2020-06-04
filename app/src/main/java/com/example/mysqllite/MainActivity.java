package com.example.mysqllite;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public
class MainActivity extends AppCompatActivity {

    private static final Object CheckedTextView = false ;
    Database mDatabase = new Database ( this );
    EditText mEditText;
    Button mButtonAddArticle;
    Button mButtonDelete;
    Button mButtonUpdate;
    ListView mListView;
    private ListAdapter myListes;
    String myArticleSelected;

    @Override
    protected
    void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mEditText = findViewById ( R.id.editTextArticles );
        mButtonAddArticle = findViewById ( R.id.btnAddArticles );
        mListView = findViewById ( R.id.listeView );
        mButtonDelete = findViewById ( R.id.buttonDelete );
        mButtonUpdate = findViewById ( R.id.buttonUpdate );




        Log.i ( "Liste", "Listes des articles dans la base : " + mDatabase.ListeNameCursor ( ) );


        /*
        Editer l'article selectionner dans la liste view
         */
        mListView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public
            void onItemClick ( AdapterView <?> parent, View view, int position, long id ) {
                Toast.makeText ( getApplicationContext(),  myListes.getItem ( position ).toString (), Toast.LENGTH_SHORT).show();
                mEditText.setText ( myListes.getItem ( position ).toString ());
                myArticleSelected = mEditText.getText ().toString ();
                mListView.setAdapter ( myListes );
                mListView.isItemChecked ( position);
            }

        } );




        /*
            Add the article in the liste view
         */
        mButtonAddArticle.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick ( View v ) {
                if (mEditText.getText ( ) != null) {
                    String myArticleSelected = mEditText.getText ( ).toString ( );
                    mDatabase.insertData ( myArticleSelected );
                    mEditText.setText ( "" );
                    MyList();
                }

            }
        } );


        /*
        update  articles
         */
        mButtonUpdate.setOnClickListener ( new View.OnClickListener ( ) {
            public
            void onClick ( View v) {
                String articles = mEditText.getText ().toString ();
                mDatabase.updateArticcles ( articles, myArticleSelected );
                mEditText.setText ( "" );
                MyList();
            }
        } );



        /*
            Delete the articles
         */
        mButtonDelete.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick ( View v ) {
                mDatabase.DeleteArticlesd (mEditText.getText ().toString ());
                mEditText.setText ( "" );
                MyList();
            }
        } );

    }

    /*
        Displays the articles in the liste view
     */
    public void MyList(){
        //myListes = new ArrayAdapter <String> ( this, android.R.layout.simple_selectable_list_item, mDatabase.ListeNameCursor ( ) );
        //myListes = new ArrayAdapter <String> ( this, android.R.layout.simple_list_item_1, mDatabase.ListeNameCursor ( ) );
        myListes = new ArrayAdapter <String> ( this, android.R.layout.simple_list_item_multiple_choice, mDatabase.ListeNameCursor ( ) );
        mListView.setAdapter ( myListes );




    }



}
