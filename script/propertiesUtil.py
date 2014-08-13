def getJavaProperties():
	return ["fbfm.stats.properties.PropertyUserTaggedFriendInPost", 
			"fbfm.stats.properties.PropertyUserLikesFriendPosts", 
			"fbfm.stats.properties.PropertyFriendTaggedUserInPost", 
			"fbfm.stats.properties.PropertyMeTaggedFriendPhoto", 
			"fbfm.stats.properties.PropertyUserSharedFriendPost", 
			"fbfm.stats.properties.PropertyFriendTaggedMePhoto", 
			"fbfm.stats.properties.PropertyFriendLikedMyPosts", 
			"fbfm.stats.properties.PropertyFriendCommentedMyPosts", 
			"fbfm.stats.properties.PropertyUserCommentedFriendPosts", 
			"fbfm.stats.properties.PropertyPrivateMessages"]



def filterProperties(filter):
	p = getJavaProperties()
	newP = []
	for i in filter:
		newP.append(p[i-1])

	return newP

def buildPriortyDict(properties, priorities):
	priorityDict = {}
	p = getJavaProperties()
	for i in range(len(properties)):
		priorityDict[p[properties[i]-1]] = priorities[i]

	return priorityDict