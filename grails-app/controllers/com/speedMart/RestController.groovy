package com.speedMart

import grails.converters.JSON

class RestController {

  def restService

  def hoodFiller() {
    Map input = request.JSON as Map

    Map validationMap = restService.validateInput(input.present_weights, input.hood_capacity)

    if (validationMap.success == "true") {
      List<Integer> weightList = input.present_weights as List<Integer>
      weightList = weightList.sort()

      List<Integer> weightListToMaintain = weightList.clone() as List<Integer>
      Integer hoodCapacity = input.hood_capacity as Integer

      render([numberOfToysPerHood: restService.getMinimumItemList(weightList, [], weightListToMaintain, hoodCapacity, hoodCapacity)]) as JSON
    } else {
      render([success: false, message: validationMap.message]) as JSON
    }
  }

}
