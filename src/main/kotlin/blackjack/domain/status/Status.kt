package blackjack.domain.status

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player

interface Status {
    fun draw(card: Card): Status
    fun stay(): Status
    fun calculateResult(dealer: Dealer): Status
}

interface PlayingStatus : Status

interface EndStatus : Status

abstract class ResultStatus(val prevPlayerStatus: Status, val prevDealerStatus: Status, val playerScore: Cards.Score, val dealerScore: Cards.Score) : Status{
    abstract fun reverse(): Status
}

class Hit(val player: Player) : PlayingStatus  {
    override fun draw(card: Card): Status {
        player.cards.addCard(card)
        if (player.cards.isBurst()) {
            return Burst(player)
        }
        if (player.cards.isBlackJackScore()) {
            return BlackJack(player)
        }
        return this
    }

    override fun stay(): Status {
        return Stay(player)
    }

    override fun calculateResult(dealer: Dealer): Status {
        throw NotImplementedError()
    }
}

class BlackJack(val player: Player) : EndStatus  {
    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }


    override fun calculateResult(dealer: Dealer): Status {
        if (dealer.status is BlackJack) {
            return Draw(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
        }
        return Win(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
    }
}

class Stay(val player: Player) : EndStatus {
    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }

    override fun calculateResult(dealer: Dealer): Status {
        if (dealer.status is Burst) {
            return Win(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
        }

        val dealerScoreDiff = dealer.cards.getScore().diff
        val playerScoreDiff = player.cards.getScore().diff

        return when {
            dealerScoreDiff > playerScoreDiff -> Win(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
            dealerScoreDiff == playerScoreDiff -> Draw(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
            else -> Lose(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
        }
    }
}

class Burst(val player: Player) : EndStatus {
    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }

    override fun calculateResult(dealer: Dealer): Status {
        if (dealer.status is Burst) {
            return Win(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
        }
        return Lose(this, dealer.status, player.cards.getScore(), dealer.cards.getScore())
    }
}
class Win(prevPlayerStatus: Status, prevDealerStatus: Status, playerScore: Cards.Score, dealerScore: Cards.Score) :
    ResultStatus(prevPlayerStatus, prevDealerStatus, playerScore, dealerScore) {
    override fun reverse() = Lose(prevPlayerStatus, prevDealerStatus, playerScore, dealerScore)


    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }

    override fun calculateResult(dealer: Dealer): Status {
        throw NotImplementedError()
    }
}

class Lose(prevPlayerStatus: Status, prevDealerStatus: Status, playerScore: Cards.Score, dealerScore: Cards.Score) :
    ResultStatus(prevPlayerStatus, prevDealerStatus, playerScore, dealerScore) {
    override fun reverse() = Win(prevPlayerStatus, prevDealerStatus, playerScore, dealerScore)

    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }

    override fun calculateResult(dealer: Dealer): Status {
        throw NotImplementedError()
    }
}

class Draw(prevPlayerStatus: Status, prevDealerStatus: Status, playerScore: Cards.Score, dealerScore: Cards.Score) :
    ResultStatus(prevPlayerStatus, prevDealerStatus, playerScore, dealerScore) {
    override fun reverse() = this

    override fun draw(card: Card): Status {
        throw NotImplementedError()
    }

    override fun stay(): Status {
        throw NotImplementedError()
    }

    override fun calculateResult(dealer: Dealer): Status {
        throw NotImplementedError()
    }
}