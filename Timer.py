import threading
import time
import tkinter as tk


class Timer:
    def __init__(self):
        self.root = tk.Tk()
        self.root.geometry('420x420')
        self.root.title('Countdown Timer')

        self.time_entry = tk.Entry(self.root, font=('Helvetica', 30))
        self.time_entry.grid(row=0, column=0, columnspan=2, padx=5, pady=5)

        self.start_button = tk.Button(self.root, font=('Helvetica', 30), text='Start', command=self.start_thread)
        self.start_button.grid(row=1, column=0, padx=5, pady=5)

        self.pause_button = tk.Button(self.root, font=('Helvetica', 30), text='Pause', command=self.pause)
        self.pause_button.grid(row=1, column=1, padx=5, pady=5)

        self.resume_button = tk.Button(self.root, font=('Helvetica', 30), text='Resume', command=self.resume)
        self.resume_button.grid(row=1, column=2, padx=5, pady=5)

        self.stop_button = tk.Button(self.root, font=('Helvetica', 30), text='Stop', command=self.stop)
        self.stop_button.grid(row=1, column=3, padx=5, pady=5)

        self.time_label = tk.Label(self.root, font=('Helvetica', 30), text='Time: 00:00:00')
        self.time_label.grid(row=2, column=0, columnspan=4, padx=5, pady=5)

        self.stop_loop = False
        self.pause_loop = False
        self.remaining_seconds = 0
        self.thread = None

        self.root.mainloop()

    def start_thread(self):
        self.stop_loop = False
        self.pause_loop = False
        hours, minutes, seconds = 0, 0, 0
        string_split = self.time_entry.get().split(':')

        if len(string_split) == 3:
            hours = int(string_split[0])
            minutes = int(string_split[1])
            seconds = int(string_split[2])
        elif len(string_split) == 2:
            minutes = int(string_split[0])
            seconds = int(string_split[1])
        elif len(string_split) == 1:
            seconds = int(string_split[0])
        else:
            print('Invalid time format')
            return

        self.remaining_seconds = hours * 3600 + minutes * 60 + seconds
        self.update_label()

    def update_label(self):
        if not self.stop_loop:
            minutes, seconds = divmod(self.remaining_seconds, 60)
            hours, minutes = divmod(minutes, 60)
            self.time_label.config(text=f'Time: {hours:02}:{minutes:02d}:{seconds:02d}')
            self.remaining_seconds -= 1
            if self.remaining_seconds >= 0:
                self.thread = threading.Timer(1, self.update_label)
                self.thread.start()
            else:
                self.stop_loop = True
                self.time_label.config(text='Time\'s up!')

    def pause(self):
        if self.thread is not None:
            self.thread.cancel()
        self.pause_loop = True

    def resume(self):
        if not self.pause_loop:
            return
        self.pause_loop = False
        self.update_label()

    def stop(self):
        if self.thread is not None:
            self.thread.cancel()
        self.stop_loop = True
        self.time_label.config(text='Time: 00:00:00')


Timer()
