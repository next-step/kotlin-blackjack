package blackjack

import blackjack.ResultView.names

fun main() {
    val deck = buildDeck()

    val players = UserInput.StringList("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)").answer()
        .map { Player.Person(it) }

    val game = BlackJackGame(players, deck)

    game.prepareDraw {
        println()
        ResultView.prepare(it)
        println()
    }

    game.draw({ name ->
        drawQuestion(name)
    }) {
        println("${it.name}카드: ${it.cards.names()}")
    }

    game.endDraw {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    ResultView.result(game)

    val gameResult = result {
        update(game.players vs game.dealer)
    }.build()

    for (result in gameResult) {
        println("${result.name}: ${ResultToString(result)}")
    }
}

class ResultToString(val playerResult: PlayerResult) {
    override fun toString(): String {
        return "${playerResult.wins}승" + "${playerResult.losses}패" + "${playerResult.draws}무"
    }
}

private fun drawQuestion(name: String): Boolean {
    return when (UserInput.Char("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)").answer()) {
        'y' -> true
        'n' -> false
        else -> throw IllegalArgumentException()
    }
}

private fun buildDeck(): Blackjack {
    return blackjack {
        ace()
        normal(2..10)
        jack()
        queen()
        king()
    }.build()
}
