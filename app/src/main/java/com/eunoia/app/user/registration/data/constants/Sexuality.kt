package com.eunoia.app.user.registration.data.constants

import androidx.compose.ui.graphics.Color
import com.eunoia.app.ui.theme.ace
import com.eunoia.app.ui.theme.bi1
import com.eunoia.app.ui.theme.bi2
import com.eunoia.app.ui.theme.bi3
import com.eunoia.app.ui.theme.gay1
import com.eunoia.app.ui.theme.gay2
import com.eunoia.app.ui.theme.gay3
import com.eunoia.app.ui.theme.gay4
import com.eunoia.app.ui.theme.gay5
import com.eunoia.app.ui.theme.gay6
import com.eunoia.app.ui.theme.panFlag1
import com.eunoia.app.ui.theme.panFlag2
import com.eunoia.app.ui.theme.panFlag3
import com.eunoia.app.ui.theme.themeLightBlue

sealed class Sexuality(var orientation: String, var txtCol: List<Color>?){
    object Straight: Sexuality("Straight", listOf(themeLightBlue, themeLightBlue))
    object Gay: Sexuality("Gay", listOf(gay1, gay2, gay3, gay4, gay5, gay6))
    object BS: Sexuality("Bi-Sexual", listOf(bi1, bi2, bi3))
    object PS: Sexuality("Pan-Sexual", listOf(panFlag1, panFlag2, panFlag3))
    object ASexual: Sexuality("A-Sexual", listOf(ace, ace))}
