import TGCHandler
import time
from sound import Sound

handler = TGCHandler.TGCHandler(host = '192.168.137.224')
handler.connect()
handler.configure()
handler.startMeasuring()
while True:
    att = handler.get('attention')
    plevel = handler.get('poorSignalLevel')
    med = handler.get('meditation')
    if att != None and plevel != None:
        print("Att:" + str(att) + " Sig:" + str(plevel) + " Med:" + str(med))
        if att > 70:
            Sound.volume_up()
        if med > 60 and att < 70:
            Sound.volume_down()
    time.sleep(1)