# example for creating a flat polygon.

doc = MQSystem.getDocument()

num = doc.numObject
obj = doc.object[0]

v = []
v.append( obj.addVertex(0,0,0) )
v.append( obj.addVertex(100,0,0) )
v.append( obj.addVertex(100,100,0) )
v.append( obj.addVertex(0,100,0) )

obj.addFace(v)

