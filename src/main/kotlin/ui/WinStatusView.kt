package ui

import domain.WinStatus

enum class WinStatusView(val winStatus: WinStatus , val ui:String) {

    WIN(WinStatus.WIN,"승"),
    LOSE(WinStatus.LOSE,"패"),
    TIE(WinStatus.TIE,"무");

    companion object {

        fun valueOf(winStatus: WinStatus) = values().single { it.winStatus == winStatus }.ui
    }
}
