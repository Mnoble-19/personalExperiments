import pytest
from cards import Card

def test_with_fail():
    c1 = Card("Hello", "world")
    c2 = Card("olleh", "dorlw")

    if c1 != c2:
        pytest.fail("false")

