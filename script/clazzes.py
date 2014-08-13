class Property:
	def __init__(self, name, value, priority):
		self.name = name
		self.value = value
		self.priority = priority
		self.normalizedValue = None

	def normalizeValue(self, maxValue):
		if maxValue != 0:
			self.normalizedValue = self.value / maxValue
		else:
			self.normalizedValue = self.value



class Row:
	def __init__(self, rowNum, properties = []):
		self.rowNum = rowNum
		self.properties = properties

	def addProperty(self, property_):
		self.properties.append(property_)

	def setFriend(self, friend):
		self.friend = friends

	def classify(self):
		c = 0
		for prop in self.properties:
			c += prop.normalizedValue * prop.priority

		self.classification = c