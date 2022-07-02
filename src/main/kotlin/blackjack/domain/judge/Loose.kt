package blackjack.domain.judge

class Loose : Judgement {
    override fun isWinner(): Boolean = false
    override fun toString(): String = "패"
}
