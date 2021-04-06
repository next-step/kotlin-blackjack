package blackjack

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val names: Names = Names(readLine()!!)

    val game: Game = Game(names)
    println("${names.joinToString(",")} 에게 2장을 나누었습니다.")

    game.players.forEach {
        print("${it.name}카드: ")
        it.cards.forEach(
            ::print
        )
        println()
    }


}

/*
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

 */
