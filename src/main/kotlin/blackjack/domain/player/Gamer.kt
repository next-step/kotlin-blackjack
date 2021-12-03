package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bet
import blackjack.domain.game.Credit

class Gamer(profile: Profile, cards: Cards = Cards.EMPTY, credit: Credit = Credit.from(0)) :
    BlackJackPlayer(profile, cards, credit) {

    override fun receiveCard(card: Card): Player {
        return Gamer(profile, cards.addCards(card), credit)
    }

    override fun turnOff(): Player {
        return Gamer(profile.turnOff(), cards, credit)
    }

    override fun turnOn(): Player {
        return Gamer(profile.turnOn(), cards, credit)
    }

    override fun isBurst(): Boolean {
        return profile.isBurst()
    }

    override fun openCards(): Cards {
        return cards
    }

    override fun getPlayerName(): Name {
        return profile.name
    }

    override fun getHighestPoint(): Int {
        return cards.getHighestPoint()
    }

    override fun canReceiveCard(): Boolean {
        return getHighestPoint() <= CAN_ACHIEVE_POINT
    }

    override fun winBetting(player: Player): Player {
        return Gamer(profile, cards, credit)
    }

    override fun holdBetting(getPlayerBetting: Bet): Player {
        return Gamer(profile, cards, Credit.from(getPlayerBetting.getPlayerBetting(this)))
    }

    override fun winBlackJack(): Player {
        return Gamer(profile, cards, credit.receiveBlackJackCredit())
    }

    override fun loseBlackJack(player: Player): Player {
        return Gamer(profile, cards, credit.subtractBlackJack(player.getPlayerCredit()))
    }

    override fun winBetting(): Player {
        return Gamer(profile, cards, credit.receiveCredit())
    }

    override fun loseBetting(): Player {
        return Gamer(profile, cards, credit.minus(credit))
    }

    override fun loseBetting(player: Player): Player {
        return Gamer(profile, cards, credit.minus(player.getPlayerCredit()))
    }

    override fun minusCredit(credit: Credit): Player {
        return Gamer(profile, cards, credit.minus(credit))
    }

    override fun receiveCredit(credit: Credit): Player {
        return Gamer(profile, cards, credit.receiveCredit(credit))
    }

    companion object {
        private const val CAN_ACHIEVE_POINT = 21
        private const val JUDGE_NOT_ALLOWED = "게이머는 판정을 할 수 없습니다"
    }
}
