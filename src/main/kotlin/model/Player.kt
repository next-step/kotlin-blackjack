package model

class Player(playerName: String) : Participant(playerName) {
    fun isExtraCard(): Boolean {
        return scoreState == ScoreState.HIT
    }
}
