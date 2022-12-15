package domain

class Player(val name: PlayerName) : GameParticipator() {

    override fun canDrawCard() = choiceBestScore() < Score.MAX_SCORE

    companion object {
        fun withName(name: String): Player = Player(PlayerName(name))
    }
}
