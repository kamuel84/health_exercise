package com.exercise.health_exercise.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.exercise.health_exercise.ExerciseApplication

class ViewUtils {
    companion object{
        @JvmStatic
        fun loadImage(url: String, requestOptions: RequestOptions?) : RequestBuilder<Drawable>{

            var options:RequestOptions ?= requestOptions

            if (options == null) {
                options = RequestOptions()
            }
            var application : ExerciseApplication = ExerciseApplication.getInstance()!!
            var context:Context = application.applicationContext

            return Glide.with(context)
                .load(url)
                .apply(options)

        }

    }
}