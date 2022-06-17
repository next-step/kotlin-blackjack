package blackjack.model.card

import blackjack.model.player.Player
import blackjack.model.player.Players

class GameHost(
    val cardSet: Cards = CardSetGenerator.generateOneCardSet(
        CardSymbol.values().toList(),
        CardNumber.values().toList()
    )
) {

    fun shuffleCards() {
        cardSet.shuffle()
    }

    fun provideCardTo(players: Players, cardCount: Int = 1) {
        players.players.map { player ->
            repeat(cardCount) { provideOneCardTo(player) }
        }
    }

    fun provideOneCardTo(player: Player) {
        validateNotExceedMaxScore(player.sumOfCardScore)

        val drawnCard = cardSet.removeOne()
        player.receiveCard(drawnCard)
    }

    private fun validateNotExceedMaxScore(score: CardScore) {
        require(score.isLessThan(MAX_SCORE)) { "카드 점수가 ${MAX_SCORE}점을 넘을 수 없습니다." }
    }

    companion object {
        private const val MAX_SCORE = 21
    }
}
