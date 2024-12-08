package blackjack.domain

sealed class Participant(val name: String, val cards: Cards) {
    abstract fun canHit(): Boolean

    abstract fun hit(card: Card): Participant

    companion object {
        fun separate(participants: List<Participant>): Pair<List<Player>, Dealer> {
            return participants.filterIsInstance<Player>() to participants.first { it is Dealer } as Dealer
        }
    }
}

class Player(name: String, cards: Cards) : Participant(name, cards) {
    override fun canHit(): Boolean {
        return cards.scoreLowerThan(PLAYER_SCORE_LIMIT)
    }

    override fun hit(card: Card): Player {
        return Player(this.name, cards.add(card))
    }

    companion object {
        private const val PLAYER_SCORE_LIMIT = 21

        fun create(name: String): Player {
            return Player(name, Cards(emptyList()))
        }
    }
}

class Dealer(cards: Cards) : Participant("딜러", cards) {
    override fun canHit(): Boolean {
        return cards.scoreLowerThan(DEALER_SCORE_LIMIT)
    }

    override fun hit(card: Card): Dealer {
        return Dealer(cards.add(card))
    }

    companion object {
        private const val DEALER_SCORE_LIMIT = 17

        fun create(): Dealer {
            return Dealer(Cards(emptyList()))
        }
    }
}
