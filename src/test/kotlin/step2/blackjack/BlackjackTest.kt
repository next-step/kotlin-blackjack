package step2.blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import step2.blackjack.domain.CardCount.getMinTotalCount
import step2.blackjack.model.Card
import step2.blackjack.model.User
import step2.blackjack.model.UserList
import step2.blackjack.model.vo.Name

/**
 * 블랙잭 테스트
 * */
class BlackjackTest: FunSpec({
    test("참가자 이름에 빈 문자열이 들어올 경우 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            User.from("")
        }
    }

    test("참가자 이름에 공백, 개행, 탭이동 문자만 들어올 경우 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            User.from(" ")
        }
        shouldThrow<IllegalArgumentException> {
            User.from("\n")
        }
        shouldThrow<IllegalArgumentException> {
            User.from("\t")
        }
    }

    test("참가자 이름 리스트에 문자열 \"pobi,,jain\"이 들어올 경우 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            UserList.from("pobi,,jain")
        }
    }

    test("참가자 이름 리스트에 문자열 \"pobi,jason,jain\"이 들어올 경우 참가자 [pobi, jason, jain]가 등록되어야 한다.") {
        val userList = UserList.from("pobi,jason,jain")
        userList.userList[0].name shouldBe Name.from("pobi")
        userList.userList[1].name shouldBe Name.from("jason")
        userList.userList[2].name shouldBe Name.from("jain")
    }

    test("참가자 [pobi]가 동일한 카드인 [2하트, 2하트]를 가지고 있으면 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            val user = User.from("pobi")
            user.cardList + Card.from("하트", "2")
            user.cardList + Card.from("하트", "2")
        }
    }

    test("참가자 [pobi, jason]에게 각각 [2하트, 8스페이드]와 [7클로버, K스페이드]를 나누어 줄 경우 pobi = [2하트, 8스페이드], jason = [7클로버, K스페이드]를 가지고 있어야 한다.") {
        val user1 = User.from("pobi")
        user1.cardList + Card.from("하트","2")
        user1.cardList + Card.from("스페이드", "8")
        user1.cardList.cardList[0] shouldBe Card.from("하트", "2")
        user1.cardList.cardList[1] shouldBe Card.from("스페이드", "8")

        val user2 = User.from("jason")
        user2.cardList + Card.from("클로버", "7")
        user2.cardList + Card.from("스페이드", "K")
        user2.cardList.cardList[0] shouldBe Card.from("클로버", "7")
        user2.cardList.cardList[1] shouldBe Card.from("스페이드", "K")
    }

    test("참가자 [pobi]가 [K하트, 4스페이드] 가지고 있는 상태에서 [5스페이드] 한장 더 받을 경우 카드가 한장 더 늘어나야 한다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "4")
        user.cardList + Card.from("스페이드", "5")
        user.cardList.cardList.size shouldBe 3
    }

    test("참가자 [pobi]가 [K하트, 4스페이드]를 가지고 있을 경우 카드를 더 받을 수 있다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "4")
        (user.cardList.getMinTotalCount() < 21) shouldBe true
    }

    test("참가자 [pobi]가 [K하트, 4스페이드, 6클로버]를 가지고 있을 경우 카드를 더 받을 수 있다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "4")
        user.cardList + Card.from("클로버", "6")
        (user.cardList.getMinTotalCount() < 21) shouldBe true
    }

    test("참가자 [pobi]가 [K하트, A스페이드, 6클로버]를 가지고 있을 경우 카드를 더 받을 수 있다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "A")
        user.cardList + Card.from("클로버", "6")
        (user.cardList.getMinTotalCount() < 21) shouldBe true
    }

    test("참가자 [pobi]가 [K하트, A스페이드, 6클로버, A하트]를 가지고 있을 경우 카드를 더 받을 수 있다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "A")
        user.cardList + Card.from("클로버", "6")
        user.cardList + Card.from("하트", "A")
        (user.cardList.getMinTotalCount() < 21) shouldBe true
    }

    test("참가자 [pobi]가 [K하트, 4스페이드, 7클로버]를 가지고 있을 경우 카드를 더 받을 수 없다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "4")
        user.cardList + Card.from("클로버", "7")
        (user.cardList.getMinTotalCount() < 21) shouldBe false
    }

    test("참가자 [pobi]가 [K하트, 4스페이드, 8클로버]를 가지고 있을 경우 카드를 더 받을 수 없다.") {
        val user = User.from("pobi")
        user.cardList + Card.from("하트", "K")
        user.cardList + Card.from("스페이드", "4")
        user.cardList + Card.from("클로버", "8")
        (user.cardList.getMinTotalCount() < 21) shouldBe false
    }
})
