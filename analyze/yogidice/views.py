from .models import (
    BggData,
    BoardGame,
    BoardGameCafe,
    Bookmark,
    CafeGameList,
    Category,
    CategoryGroup,
    History,
    Mechanism,
    MechanismGroup,
    Type,
    TypeGroup,
    User
)
from .serializers import (
    BggDataSerializer,              ## BggData
    BoardGameSerializer,            ## BoardGame
    BoardGameCafeSerializer,        ## BoardGameCafe
    BookmarkSerializer,             ## Bookmark
    CafeGameListSerializer,         ## CafeGameList
    CategorySerializer,             ## Category
    CategoryGroupSerializer,        ## CategoryGroup
    HistorySerializer,              ## History
    MechanismSerializer,            ## Mechanism
    MechanismGroupListSerializer,   ## MechanismGroup
    TypeSerializer,                 ## Type
    TypeGroupSerializer,            ## TypeGroup
    UserSerializer                  ## Type
) 
from rest_framework.decorators import api_view
from rest_framework.response import Response 
from rest_framework.status import (
    HTTP_201_CREATED,
    HTTP_204_NO_CONTENT,
)



@api_view(['GET'])
def bggdata_list(request):
    if request.method == 'GET':
        name = BggData.objects.all()
        serializer = BggDataSerializer(name, many=True)
        return Response(serializer.data)


@api_view(['GET'])
def boardgame_list(request):
    if request.method == 'GET':
        name = BoardGame.objects.all()
        serializer = BoardGameSerializer(name, many=True)
        return Response(serializer.data)