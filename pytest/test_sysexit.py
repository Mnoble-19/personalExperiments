import pytest


def f():
    raise SystemExit(1)


# How to test that a certain exception is raised
def test_mytest():
    with pytest.raises(SystemExit):
        f()


def f1():
    raise ExceptionGroup(
        "Group Messo",
        [
            RuntimeError(),
        ]
    )


def test_exception_in_group():
    with pytest.raises(ExceptionGroup) as exc_info:
        f1()
    assert exc_info.group_contains(RuntimeError)
    assert exc_info.group_contains(TypeError)