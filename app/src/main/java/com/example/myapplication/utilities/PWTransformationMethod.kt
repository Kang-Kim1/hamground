package com.example.myapplication.utilities

import android.text.method.PasswordTransformationMethod
import android.view.View

open class PWTransformationMethod : PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        return object : CharSequence {
            override val length: Int
                get() = source.length

            override fun get(index: Int) = '*'

            override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                return source.subSequence(startIndex, endIndex)
            }
        }
    }
}