package blackjack.view

object OutputView {
    fun showStartStatus(players: List<PlayerResponseDto>) {
        println("${players.joinToString(", ") { it.name }}에게 2장씩 나누었습니다.")
        for (player in players) {
            showPlayerStatus(player)
        }
        println()
    }

    fun showPlayerStatus(player: PlayerResponseDto) {
        println("${player.name}카드: ${showCards(player.cards)}")
    }

    private fun showCards(cards: List<PlayerResponseDto.CardResponseDto>): String {
        return cards.joinToString(", ") { "${it.denomination}${it.suitName}" }
    }

    fun showResult(players: List<PlayerResponseDto>) {
        for (player in players) {
            showPlayerResult(player)
        }
    }

    private fun showPlayerResult(player: PlayerResponseDto) {
        println("${player.name}카드: ${showCards(player.cards)} - 결과: ${player.score}")
    }
}
