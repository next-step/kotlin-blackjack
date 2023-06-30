package blakjack.domain

class Player(
    name: String,
) : Participant(name) {
    var result: Result = Result.NONE
        private set

    override fun win(other: Participant) {
        super.win(other)
        result = Result.WIN
    }

    override fun lose() {
        result = Result.LOSE
    }

    enum class Result {
        WIN, LOSE, NONE
    }
}
