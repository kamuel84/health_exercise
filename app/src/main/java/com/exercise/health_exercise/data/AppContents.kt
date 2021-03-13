package com.exercise.health_exercise.data

import android.app.Activity

class AppContents {
    companion object{
        const val REQUEST_CODE_ADDLIST = 1000
        const val REQUEST_CODE_LISTDETAIL = 2000

        const val INTENT_DATA_LIST_INDEX = "INTENT_DATA_LIST_INDEX"
        const val INTENT_DATA_LIST_POSITION = "INTENT_DATA_LIST_POSITION"
        const val INTENT_DATA_EDIT_MODE = "INTENT_DATA_EDIT_MODE"

        const val RESULT_DATA_TITLE = "RESULT_DATA_TITLE"
        const val RESULT_DATA_LISTDATA = "RESULT_DATA_LIST_DATA"
        const val RESULT_DATA_HEALTHLIST = "RESULT_DATA_HEATHLIST"

        const val VIEWTYPE_DATEHADER = 0
        const val VIEWTYPE_PLAYITEM = 1
    }
}