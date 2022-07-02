package blackjack.domain.judge

class Win : Judgement {
    override fun isWinner(): Boolean = true

    override fun toString(): String = "승"
}
