package domain.gamer.player

import domain.card.CardDeck

data class Players(val list: List<Player>) {

    fun hit(cardDeck: CardDeck) {
        list.forEach {
            it.hit(cardDeck)
        }
    }

    fun notBustedPlayers(): Players {
        return Players(
            list.filterNot { it.isBust }
        )
    }

    fun results(): List<Int> {
        return list.map { it.score }
    }

    fun playersWithMatchedScore(score: Int): Players {
        return Players(
            list.filter {
                it.score == score
            }
        )
    }

    fun playersWithMismatchedScore(score: Int): Players {
        return Players(
            list.filterNot {
                it.score == score
            }
        )
    }
}
