package blackjack

import blackjack.ResultView.names

fun main() {
    val deck = buildDeck()

    val players = UserInput.StringList("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)").answer()
        .map { CardPlayer.Player(it) }
        .let { Players(it) }

    val bettings = players.map {
        it to UserInput.Int("${it.name}의 배팅 금액은?").answer()
    }.map { (player, money) -> Bet(player, money) }

    val game = BlackJackGame(players, deck)
    game.prepareDraw()

    ResultView.prepare(players)

    game.draw({ name ->
        drawQuestion(name)
    }) {
        println("${it.name}카드: ${it.hand.names()}")
    }

    game.endDraw {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    ResultView.result(players, bettings)
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
