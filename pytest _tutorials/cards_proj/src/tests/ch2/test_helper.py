import pytest
from cards import Card


def assert_identical(c1: Card, c2: Card):
    # __tracebackhide__ = True
    assert c1 == c2
    if c1.id != c2.id:
        pytest.fail('don\'t match')


def test_identical_fail():
    c1 = Card("nob", id=123)
    c2 = Card("nob", id=456)
    assert_identical(c1, c2)


def test_identical():
    c1 = Card("nob", id=123)
    c2 = Card("nob", id=123)
    assert_identical(c1, c2)
