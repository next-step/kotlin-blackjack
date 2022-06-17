package blackjack.model.card

import blackjack.model.player.Player

class GameHost(
    val cardSet: Cards = CardSetGenerator.generateOneCardSet(
        CardSymbol.values().toList(),
        CardNumber.values().toList()
    )
) {

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
