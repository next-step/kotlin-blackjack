package blackjack

class Scores(val value: List<Score>) {

    companion object {
        fun from(value: Int): Scores {
            return Scores(listOf(Score(value)))
        }
    }
}
