package blackjack.domain

class Participants(val participants: List<Participant>) {
    operator fun plus(other: Participants): Participants {
        return Participants(participants + other.participants)
    }

    fun toList(): List<Participant> {
        return participants.toList()
    }

    fun filter(predicate: (Participant) -> Boolean): Participants {
        return Participants(participants.filter(predicate))
    }

    fun getDealer(): Dealer {
        return participants.filterIsInstance<Dealer>().first()
    }

    fun getPlayers(): List<Player> {
        return participants.filterIsInstance<Player>()
    }

    fun isDealerBust(): Boolean {
        return getDealer().cards.isBust()
    }

    fun calculateDealerResult(): DealerResult {
        val dealer = getDealer()

        getPlayers().forEach {
            if (it.isWin(dealer)) {
                dealer.dealerResult.lose += 1
                return@forEach
            }
            if (it.isLose(dealer)) {
                dealer.dealerResult.win += 1
                return@forEach
            }
            dealer.dealerResult.draw += 1
        }

        return dealer.dealerResult
    }
}
