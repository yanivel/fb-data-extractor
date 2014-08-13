import sys, getopt, propertiesUtil
from xlrd import open_workbook
from clazzes import Row, Property


def howToUse():
	print "Use flags:"
	print " -f for xsl properties Filename"
	print " -n for xsl feature filename"
	print " -p for Properties"
	print " -r for pRiorities"
	print ""
	print "for example:"
	print "- calculateExamples.py -f \"myProperties.xlsx\" -n \"myFeatures.xlsx\" -p \"[1,2,4,5,10]\" -r \"[3,3,20,1,1]\""
	print ""
	print "the available properties are: "
	printAvailableProperties()


def printAvailableProperties():
	props = propertiesUtil.getJavaProperties();
	for i in range(len(props)):
		print (i+1), " : " , props[i]

def processAndClassify(features_filename, properties_filename, properties, priorities):

	properties_workbook = open_workbook(properties_filename)	
	worksheet = properties_workbook.sheet_by_index(0)
	print "number of rows : " , worksheet.nrows
	print "number of cols : " , worksheet.ncols
	# key: property name, value: priority
	priorityDict = propertiesUtil.buildPriortyDict(properties, priorities)
	# key: col number, value: property name
	propertyColDict = {}
	for col_index in range(worksheet.ncols):
		value = worksheet.cell(0,col_index).value
		if value in propertiesUtil.filterProperties(properties):
			propertyColDict[col_index] = value

	# array of Row objects key: friend name value: row object
	rows = {}

	# get max value for each property for normalization ( assume minimal value is 0)
	propertyMaxValue = {}
	for row_index in range(worksheet.nrows):
		if row_index == 0:
				continue
		for col_index in range(worksheet.ncols):
			if col_index not in propertyColDict:
				continue
			propertyName = propertyColDict[col_index]
			if propertyName in priorityDict:
				newValue = worksheet.cell(row_index, col_index).value
				if propertyName in propertyMaxValue:
					oldMaxValue = propertyMaxValue[propertyName]
					if oldMaxValue < newValue:
						propertyMaxValue[propertyName] = newValue
				else:
					propertyMaxValue[propertyName] = newValue

	# generate Row array with properties (need to normalize after to get classification)
	for row_index in range(worksheet.nrows):
		if (row_index == 0):
			continue

		row = Row(row_index, [])
		friend = ''
		for col_index in range(worksheet.ncols):
			if col_index == 1: # friend col
				friend = worksheet.cell(row_index,col_index).value
			elif col_index > 1: # properties cols
				if col_index in propertyColDict:
					propertyName = propertyColDict[col_index]
					row.addProperty( Property(propertyName, worksheet.cell(row_index,col_index).value , priorityDict[propertyName]) )

		rows[friend] = row
	
	print 'priority dict :             '  , priorityDict
	#normalize all the properties in the rows
	for rowNum, rowObj in rows.iteritems():
		for prop in rowObj.properties:
			prop.normalizeValue( propertyMaxValue[prop.name] )

	# classify all rows
	for rowNum, rowObj in rows.iteritems():
		rowObj.classify()

	# now push everything to a new features csv file with the classification
	features_workbook = open_workbook(features_filename)
	featuresheet = features_workbook.sheet_by_index(0)

	file_ptr = open(features_filename +'_'+ str(priorities) + '_with_classify.csv','w')
 
	for row_index in range(featuresheet.nrows):
		friend = ''
		if (row_index > (worksheet.nrows-1)):
			break
		for col_index in range(featuresheet.ncols):
			if (col_index == 1):
				friend = featuresheet.cell(row_index, col_index).value
			file_ptr.write( str(featuresheet.cell(row_index, col_index).value) )
			if col_index < (featuresheet.ncols - 1):
				file_ptr.write( ',') 
			elif col_index == (featuresheet.ncols - 1):
				if row_index == 0:
					file_ptr.write(',class\n')
				else:
					file_ptr.write(',' + str(rows[friend].classification) + '\n')

		
	file_ptr.close()


def main(argv):

	properties_filename = ""
	features_filename = ""
	properties = []
	priorties = []

	try:
		opts, args = getopt.getopt(argv,"hp:r:f:n:")
	except getopt.GetoptError:
		print '- calculateExamples.py -f <properties filename> -n <feature filename> -p <properties array> -r <priority array>'
		print 'getOptError'
		sys.exit(2)

	for opt, arg in opts:
		if opt in ('-h'):
			howToUse()
			sys.exit()
		elif opt in ("-p"):
			properties = eval(arg);
		elif opt in ("-r"):
			priorities = eval(arg);
		elif opt in ("-f"):
			properties_filename = arg;
		elif opt in ("-n"):
			features_filename = arg;
	print 'properties are ', properties
	print 'priorites are ', priorities
	print 'property filename is ', properties_filename
	print 'features filename is ' , features_filename

	processAndClassify(features_filename, properties_filename, properties, priorities)

if __name__ == "__main__":
	main(sys.argv[1:])


									
