
import Jetson.GPIO as GPIO
import time

def sensor_reader():

    input_pin = 40  

    prev_value = None

    GPIO.setmode(GPIO.BOARD)  
    GPIO.setup(input_pin, GPIO.IN)  
    print("Press CTRL+C to exit")
    try:
        while True:
            value = GPIO.input(input_pin)
            
            if value == GPIO.HIGH:
                value_str = "CLOSED"

            else:
                value_str = "OPEN"
            print("DOOR {}".format(value_str))

            time.sleep(2)
    finally:
        GPIO.cleanup()


