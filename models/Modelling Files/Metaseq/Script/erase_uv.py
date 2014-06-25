# example for erasing UV

doc = MQSystem.getDocument()

num = doc.numObject
for x in range(0,num):
	obj = doc.object[x]
	if obj is None: continue
	numFace = obj.numFace
	for x in range(0,numFace):
		for y in range(0,obj.face[x].numVertex):
			if obj.face[x].select:
				obj.face[x].coord[y] = MQSystem.newCoordinate(0,0)
