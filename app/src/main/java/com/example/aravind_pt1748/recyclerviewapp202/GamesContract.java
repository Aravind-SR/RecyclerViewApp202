package com.example.aravind_pt1748.recyclerviewapp202;

/**
 * Created by aravind-pt1748 on 26/03/18.
 */

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class GamesContract {

    public static final String AUTHORITY = "com.example.aravind_pt1748.CPApp202.data.database";
    public static final String PATH_NAME = "Games";
    public static final String DATABASE_NAME = "GamesDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final int ALL_ITEMS = -200;

    private GamesContract(){

    }

    public static abstract class GamesMetaData implements BaseColumns {

        public static final String TABLE_NAME = "GamesTable";

        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+PATH_NAME);

        public static final String GROUP_ITEM_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+AUTHORITY+"gamesBundle";
        public static final String SINGLE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+AUTHORITY+"oneGameOnly";

        public static final String GAME_NAME = "gameName";
        public static final String GENRE_NAME = "genres";
        public static final String RELEASE_DATE = "releaseDate";
        public static final String IMAGE_GAME = "imageBlob";
    }
}
