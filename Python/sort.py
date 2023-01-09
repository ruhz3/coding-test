#
# 기본 정렬과 선택 정렬 알고리즘 속도 비교
#
from random import randint
import time
import copy


def selection_sort(array):
    """
    선택 정렬
    :param array:
    :return:
    """
    for i in range(len(array)):
        min_index = i
        for j in range(i+1, len(array)):
            if array[min_index] > array[j]:
                min_index = j
        array[i], array[min_index] = array[min_index], array[i]


def default_sort(array):
    """
    기본 정렬
    :param array:
    :return:
    """
    array.sort()


def check(arr, func):
    """
    정렬을 수행하고, 경과 시간을 출력한다.
    :param arr:
    :param func:
    :return:
    """
    # 시작 시간 기록
    start_time = time.time()
    func(arr)
    # 종료 시간 기록
    end_time = time.time()
    print(f"Ellapsed Time : {end_time - start_time}")


# 1부터 100 사이의 정수 10000개 생성
raw_array = []
for _ in range(10000):
    raw_array.append(randint(1, 100))

check(copy.deepcopy(raw_array), selection_sort)
check(copy.deepcopy(raw_array), default_sort)
