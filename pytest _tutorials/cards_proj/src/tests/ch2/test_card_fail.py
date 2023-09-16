from cards import Card

def test_equality_fail():
    c1 = Card("rizz GPT", "rizz")
    c2 = Card("chad GPT", "chad")
    assert c1 == c2