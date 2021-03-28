package blackjack.domain

import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

class Dealer(
    private val players: Players,
    private val cardPack: CardPack,
    cards: Set<Card> = emptySet()
) : Participant {

    private val player = Player("딜러", cards)
    val cardResult: PlayerCardResult
        get() = PlayerCardResult(player)
    val isUnderSixteen: Boolean
        get() = this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD

    fun giveTwoCardsToAllPlayers(): Players {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
            player.takeCard(cardPack.poll())
        }
        return players
    }

    fun giveCard(player: Player, hasAccepted: Boolean): Player {
        if (hasAccepted) {
            player.takeCard(cardPack.poll())
        }
        return player
    }

    fun takeCardIfUnderSixteen(): Boolean {
        if (this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD) {
            this.takeCard(cardPack.poll())
            return true
        }
        return false
    }

    fun findPlayerWinTypes(): PlayerWinTypes {
        val winTypeMap = mutableMapOf<String, PlayerWinType>()
        val dealerPoint = this.cardPointSum()
        players.forEach { winTypeMap[it.name] = PlayerWinType.findPlayerWinType(it.cardPointSum(), dealerPoint) }
        return PlayerWinTypes(winTypeMap)
    }

    fun takeCard() {
        takeCard(cardPack.poll())
    }

    override fun takeCard(card: Card) {
        player.takeCard(card)
    }

    override fun cardPointSum(): Int {
        return player.cardPointSum()
    }

    override fun toPlayerDto(): PlayerDto {
        return player.toPlayerDto()
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
