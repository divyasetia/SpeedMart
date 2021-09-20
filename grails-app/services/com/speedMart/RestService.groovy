package com.speedMart

import grails.transaction.Transactional

@Transactional
class RestService {

  Map validateInput(def weights, def hoodCapacity) {
    Map<String, String> validationMap = [:]

    if (!weights) {
      validationMap.put("success", "false")
      validationMap.put("message", "List of items weights is missing from the input")
    } else if (weights && !(weights instanceof List)) {
      validationMap.put("success", "false")
      validationMap.put("message", "Invalid Weight list passed as an input")
    } else if (!hoodCapacity) {
      validationMap.put("success", "false")
      validationMap.put("message", "Hood Capacity is missing from the input")
    } else if (hoodCapacity && !(hoodCapacity instanceof Integer)) {
      validationMap.put("success", "false")
      validationMap.put("message", "Invalid Hood capacity passed as an input. It must be Integer")
    } else {
      validationMap.put("success", "true")
    }

    return validationMap
  }

  List getMinimumItemList(List<Integer> weights, List<Integer> toysWeight, List<Integer> weightsToMaintain, int capacity, int remainder) {
    Integer count = 0

    while (remainder > 0) {
      count = weights.findAll { it <= remainder }.max()

      if (count) {
        toysWeight.add(count)
        remainder = remainder - count
      } else {
        break
      }
    }

    if (remainder) {
      int leftValue = toysWeight.remove(toysWeight.size() - 1)
      remainder = remainder + leftValue

      if (remainder == capacity) {
        toysWeight = []
        weights = weightsToMaintain.findAll { it < leftValue }
      } else {
        if (weights) {
          weights.remove(weights.size() - 1)
        } else {
          weights = [weightsToMaintain[0]]
        }
      }

      getMinimumItemList(weights, toysWeight, weightsToMaintain, capacity, remainder)
    } else {
      return toysWeight
    }
  }


}
