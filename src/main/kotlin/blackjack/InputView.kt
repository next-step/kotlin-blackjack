package blackjack

object InputView {
    fun shareCard(cardDeck: CardDeck): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(",").map { Player(it.trim()) }

        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

        for (player in players) {
            player.getInitialCards(cardDeck.drawIntialCards())
        }

        return players
    }

    fun isHitCalled(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
