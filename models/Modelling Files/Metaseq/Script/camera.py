doc = MQSystem.getDocument()
scene = doc.getScene(0)

orgfov = scene.fov
scene.setCameraPos(MQSystem.newPoint(500,500,500))
scene.setLookAtPos(MQSystem.newPoint(0,500,0), MQSystem.newPoint(0,1,0))
scene.fov = orgfov

pos = scene.getCameraPos()
angle = scene.getCameraAngle()
lookat = scene.getLookAtPos()
upvec = scene.getLookAtUpVec()
""" upvecÇÕâ∫ÇÃéÆÇ≈Ç‡åvéZâ¬î\
viewvec = (lookat - pos)
viewvec.normalize()
upvec = angle.getMatrix().mult(MQSystem.newPoint(0,1,0))
upvec.z = -upvec.z #éãñÏç¿ïWånÇÕZé≤Ç™ãt
"""
center = scene.getRotationCenter()
fov = scene.fov

MQSystem.println("pos: " + str(pos))
MQSystem.println("angle: " + str(angle))
MQSystem.println("lookat: " + str(lookat))
MQSystem.println("upvec: " + str(upvec))
MQSystem.println("center: " + str(center))
MQSystem.println("fov: %(#).3f" % {"#":fov})
