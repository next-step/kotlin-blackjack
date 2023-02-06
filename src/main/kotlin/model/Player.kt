package model

class Player(playerName: String) : Participant(playerName) {
    fun isExtraCard(): Boolean {
        return sumOfCardNumber < BLACK_JACK
    }
}
