package com.speedMart

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RestService)
class RestServiceSpec extends Specification {
  final List weights = [2, 5, 10, 50, 100]

  def setup() {
  }

  def cleanup() {
  }

  void "test getMinimumItemList11"() {
    expect:

    List<Integer> output = service.getMinimumItemList(weights.clone() as List<Integer>, [], weights, capacity, capacity)
    output == expectedOutput

    where:
    capacity | expectedOutput
    11       | [5, 2, 2, 2]
    12       | [10, 2]
    13       | [5, 2, 2, 2, 2]
    14       | [10, 2, 2]
    15       | [10, 5]
    16       | [10, 2, 2, 2]
    17       | [10, 5, 2]
    18       | [10, 2, 2, 2, 2]
    19       | [10, 5, 2, 2]
    20       | [10, 10]
  }


}
