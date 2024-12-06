package blackjack

class InputView {
    fun inputBlackJack(): BlackJackGame {
        val players = inputPlayerNames()
        val blackJackGame = BlackJackGame(players)
        println()

        val initialDrawResult = blackJackGame.initialDraw()
        printInitialDraw(initialDrawResult)
        printDrawResult(initialDrawResult)
        println()

        additionalDraw(blackJackGame)
        println()
        return blackJackGame
    }

    private fun inputPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputPlayerNames = readlnOrNull()
        return inputPlayerNames
                ?.split(",")
                ?.map { Player(PlayerName(it)) }
                ?: throw IllegalArgumentException("게임에 참여할 사람의 이름은 필수 입니다.")
    }

    private fun printInitialDraw(inititalDrawResult: List<DrawResult>) {
        val playerNames = inititalDrawResult.joinToString(",") { it.playerName }
        println("${playerNames}에게 2장을 나누었습니다.")
    }

    private fun printDrawResult(drawResult: List<DrawResult>) {
        drawResult.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> drawCard.drawCardString }
            println("${it.playerName}카드: $displayCards")
        }
    }

    private fun additionalDraw(blackJackGame: BlackJackGame) {
        while (blackJackGame.canDrawForAllPlayers()) {
            val playerName = blackJackGame.findDrawPlayer()
            val needAdditionalDraw = inputNeedAdditionalDraw(playerName)

            if (needAdditionalDraw) {
                val drawCard = blackJackGame.drawCard(playerName)
                printDrawResult(listOf(drawCard))
            } else {
                blackJackGame.stopDraw(playerName)
            }
        }
    }

    private fun inputNeedAdditionalDraw(playerName: PlayerName): Boolean {
        println("${playerName.value}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        val input = readlnOrNull()
        return !(input.isNullOrBlank() || input == "n")
    }
}