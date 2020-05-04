

import logic
import pygame as pg

length = 60
GREEN =(0,255,0)
GREY = (200,200,200)
WHITE = (255,255,255)
BLACK = (0,0,0)
BEIGE = (128,64,10)

class square(object):
    def __init__(self, pos,piece = None):
        self.type = piece
        self.pos = pos
        self.active = 0
        
   
    def draw(self,i,j, screen, colour):
        if not self.active:
            pg.draw.rect(screen, colour, (i*(length), j*(length),length,length))
        else:
            pg.draw.rect(screen, GREEN, (i*(length), j*(length),length,length))

class piece(object):
    def __init__(self, colour = None):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 1
        self.img = None
    
    def print(self,pos,screen):
        if self.colour:
            if self.img:
                screen.blit(self.img, logic.posToPixel(pos))
                
        
    
    
    
    
class pawn(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 1
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_pdt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_plt60.png')
            

    
    def move(self, pos):

        move = []
        temp = 0
        if self.colour == BLACK:
            if pos[0] == 1:
                self.moves = 2
                temp = logic.addT(pos, 2, 0)
                if temp[0] <8 and temp[0] >=0:
                    if temp[1] <8 and temp[1] >=0:
                        move.append(temp)
            temp = logic.addT(pos, 1, 0)
            if temp[0] <8 and temp[0] >=0:
                if temp[1] <8 and temp[1] >=0:
                    move.append(temp)
        else:
            if pos[0] == 6:
                self.moves = 2
                temp = logic.addT(pos, -2, 0)
                if temp[0] <8 and temp[0] >=0:
                    if temp[1] <8 and temp[1] >=0:
                        move.append(temp)
            temp = logic.addT(pos, -1, 0)
            if temp[0] <8 and temp[0] >=0:
                if temp[1] <8 and temp[1] >=0:
                    move.append(temp)
        return move        
        
   
    
        
class king(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_Kdt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_klt60.png')
    def move(self, pos):
            move = []
            temp = 0
            for i in range(-1,2,2):
                temp = logic.addT(pos, i,0)
                if temp[0] <8 and temp[0] >=0:
                    move.append(temp)
                    self.moves +=1
                temp = logic.addT(pos, 0,i)
                if temp[1] <8 and temp[1] >=0:
                    move.append(temp)
                    self.moves +=1
            
            return move
    
class queen(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_qdt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_qlt60.png')
    def move(self, pos):
        move = []
        temp = 0
        for i in range(0,8):
            temp = logic.addT(pos, i,i) 
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, i,-i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, -i,i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, -i,-i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            for i in range(-7,8):
                temp = logic.addT(pos, i,0)
                if temp[0] <8 and temp[0] >=0:
                    move.append(temp)
                    self.moves +=1
                temp = logic.addT(pos, 0,i)
                if temp[1] <8 and temp[1] >=0:
                    move.append(temp)
                    self.moves +=1
        return move
    
class rook(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_rdt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_rlt60.png')
    def move(self, pos):
        self.moves = 0
        move = []
        temp = 0
        for i in range(-7,8):
            temp = logic.addT(pos, i,0)
            if temp[0] <8 and temp[0] >=0:
                move.append(temp)
                self.moves +=1
            temp = logic.addT(pos, 0,i)
            if temp[1] <8 and temp[1] >=0:
                move.append(temp)
                self.moves +=1
        return move

class bishop (piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_bdt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_blt60.png')
    def move(self, pos):
        move = []
        temp = 0
        for i in range(0,8):
            temp = logic.addT(pos, i,i) 
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, i,-i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, -i,i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
            temp = logic.addT(pos, -i,-i)
            if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
                move.append(temp)
                
                self.moves +=1
        
        return move
    
class knight(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        if self.colour == BLACK:
            self.img =  pg.image.load('img\Chess_ndt60.png')
        elif self.colour == WHITE:
            self.img =  pg.image.load('img\Chess_nlt60.png')
    def move(self, pos):
        move = []
        temp = 0
        temp = logic.addT(pos, 2,1)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, 2,-1)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, -2,1)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, -2,-1)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, 1,2)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, 1,-2)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, -1,2)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        temp = logic.addT(pos, -1,-2)
        if temp[0] <8 and temp[1] >=0 and temp[0] >=0 and temp[1] <8:
            move.append(temp)
            self.moves +=1
        
        return move
    
class empty(piece):
    def __init__(self, colour):
        self.colour = colour
        self.font = pg.font.Font('freesansbold.ttf', 50)
        self.moves = 0
        self.img = " "
    def move(self, pos):
        return None