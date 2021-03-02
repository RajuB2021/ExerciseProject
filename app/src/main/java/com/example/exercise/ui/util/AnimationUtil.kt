package com.example.exercise.ui.util

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.exercise.R

class AnimationUtil {

    companion object {
        val RIGHT_IN_ANIMATION: Int = 1
        val LEFT_IN_ANIMATION: Int = 2

        fun getAnimation(appContext: Context,
                         animationtype: Int
        ): Animation? {
            var myAnim: Animation? = null
            when (animationtype) {
                RIGHT_IN_ANIMATION -> myAnim = AnimationUtils.loadAnimation(appContext, R.anim.right_in)
                LEFT_IN_ANIMATION -> myAnim = AnimationUtils.loadAnimation(appContext, R.anim.left_in)
                else -> myAnim = AnimationUtils.loadAnimation(appContext, R.anim.right_in)
            }
            return myAnim
        }
    }

}