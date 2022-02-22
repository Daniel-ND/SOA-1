Vue.component(
    'result-table',
    {
        template:
            `
                <div>
                    <h1 class="col-xs-1 text-center">Lab works</h1>
                    <table class="table table-striped table-bordered table-hover p-5 text-center">
                        <tr>
                            <th>Id
                                <button @click="sortById()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Name
                                <button @click="sortByName()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Coordinates
                                <button @click="sortByCoordinates()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Creation date
                                <button @click="sortByDate()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Minimal point
                                <button @click="sortByMinimalPoint()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Difficulty
                                <button @click="sortByDifficulty()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Author
                                <button @click="sortByAuthor()">
                                    <object data="unfold_more.svg" width="15" height="15"> </object>
                                </button>
                            </th>
                            <th>Actions</th>
                        </tr>
                        <tr v-for="labWork in paginatedLabs">
                            <td>{{ labWork.id }}</td>
                            <td>{{ labWork.name }}</td>
                            <td>Id: {{ labWork.coordinates.id }} (X: {{ labWork.coordinates.x }}. Y: {{ labWork.coordinates.y }})</td>
                            <td>{{ labWork.creationDate }}</td>
                            <td>{{ labWork.minimalPoint }}</td>
                            <td>{{ labWork.difficulty }}</td>
                            <td v-if="labWork.author">Id: {{ labWork.author.id }} (name: {{ labWork.author.name }})</td>
                            <td v-else>None</td>
                            <td>
                                <button class="btn btn-success" v-on:click="editLabWork(labWork)" type="submit">Edit</button>
                                <button class="btn btn-danger" v-on:click="deleteLabWork(labWork)" type="submit">Delete</button>
                            </td>
                        </tr>
                    </table>
                    <div class="v-table_pagination">
                        <div class="page"
                        v-for="page in pages" :key="page"
                        :class="{'page_selected': page === pageNumber}"
                        @click="pageClick(page)">
                            {{page}}
                        </div>
                    </div>
                </div>
            `,

        props: ["labworks"],
        data: function() {
            return {
                labsPerPage: 4,
                pageNumber: 1
            }
        },
        computed:{
            pages() {
                return Math.ceil(this.labworks.length / this.labsPerPage);
            },
            paginatedLabs() {
                let from = (this.pageNumber - 1) * this.labsPerPage;
                let to = from + this.labsPerPage;
                return this.labworks.slice(from, to);
            }
        },
        methods: {
            pageClick: function (page) {
                this.pageNumber = page;
            },
            editLabWork: function (labWork) {
                this.$emit('editlab', labWork);
            },
            deleteLabWork: function (labWork) {
                this.$emit('deletelab', labWork);
            },
            formatCreationDate: function (labWork) {
                return moment(labWork.creationDate, 'lll').format('YYYY-MM-DD');
            },
            sortByName: function() {
                this.labworks.sort((a, b) => a.name.localeCompare(b.name))
            },
            sortById: function() {
                this.labworks.sort((a, b) => a.id - b.id)
            },
            sortByCoordinates: function() {
                this.labworks.sort((a, b) => a.coordinates.id - b.coordinates.id)
            },
            sortByDate: function() {
                this.labworks.sort((a, b) => a.id.localeCompare(b.id))
            //    NORMAL DATES PLEASE
            },
            sortByMinimalPoint: function() {
                this.labworks.sort((a, b) => a.minimalPoint - b.minimalPoint)
            },
            sortByDifficulty: function() {
                const difficultyOrder = ['EASY', 'NORMAL', 'HARD', 'IMPOSSIBLE', 'HOPELESS']
                this.labworks.sort((a, b) => difficultyOrder.indexOf(a.difficulty) - difficultyOrder.indexOf(b.difficulty))
            },
            sortByAuthor: function() {
                this.labworks.sort((a, b) => a.author.id - b.author.id)
            }
        }
    }
);

Vue.component(
    'filters',
    {
        template:
            `
                <div>
                <h1 class="col-xs-1 text-center">Filters</h1>
                <div>
                    <label for="name">Id</label>
                    <input id="name" type="text" maxlength="256" v-model="id">
                </div>
                <div>
                    <label for="name">Name</label>
                    <input id="name" type="text" maxlength="256" v-model="name">
                </div>
                <div>
                    <label for="coordinates">Coordinates id</label>
                    <input id="coordinates" type="text" maxlength="8" v-model="coordinate_id">
                </div>
                <div>
                    <label for="minimalPoint">Minimal point</label>
                    <input id="minimalPoint" type="text" maxlength="8" v-model="minimalPoint">
                </div>
                <div>
                    <label for="difficulty">Difficulty</label>
                    
                    <label for="difficultyEASY" class="pl-5">EASY</label>
                    <input id="difficultyEASY" type="radio" value="EASY" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyNORMAL" class="pl-5">NORMAL</label>
                    <input id="difficultyNORMAL" type="radio" value="NORMAL" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyHARD" class="pl-5">HARD</label>
                    <input id="difficultyHARD" type="radio" value="HARD" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyIMPOSSIBLE" class="pl-5">IMPOSSIBLE</label>
                    <input id="difficultyIMPOSSIBLE" type="radio" value="IMPOSSIBLE" v-model="difficulty" name="difficulty">
                   
                    <label for="difficultyHOPELESS" class="pl-5">HOPELESS</label>
                    <input id="difficultyHOPELESS" type="radio" value="HOPELESS" v-model="difficulty" name="difficulty">
                </div>
            
                <div>
                    <label for="author">Author id</label>
                    <input id="author" type="text" maxlength="8" v-model="person_id">
                </div>
                
                <button class="btn btn-info" v-on:click="updateTable()" type="submit">Update table</button>
            </div>
            `,
        data: function() {
            return {
                id: '',
                name: '',
                coordinate_id: '',
                minimalPoint: '',
                difficulty: '',
                person_id: ''
            }
        },
        methods: {
           updateTable() {
               let filter_values = {
                   'id': this.id,
                   'name': this.name,
                   'coordinate_id': this.coordinate_id,
                   'minimalPoint': this.minimalPoint,
                   'difficulty': this.difficulty,
                   'person_id': this.person_id
               }
               this.$emit('updatetable', filter_values)
               this.clearFields()
           },
            clearFields:function(){
                this.id = ""
                this.name = ""
                this.coordinate_id = ""
                this.minimalPoint = ""
                this.difficulty = ""
                this.person_id = ""
            }
        }
    }
);

Vue.component(
    'add-lab-work',
    {
        template:
            `
            <div>
                <h1 class="col-xs-1 text-center">Add/update lab work</h1>
                <div>
                    <label for="name">Name</label>
                    <input id="name" type="text" maxlength="256" v-model="name">
                </div>
                <div>
                    <label for="coordinates">Coordinates</label>
                    <input id="coordinates" type="text" maxlength="8" v-model="coordinates">
                </div>
                <div>
                    <label for="minimalPoint">Minimal point</label>
                    <input id="minimalPoint" type="text" maxlength="8" v-model="minimalPoint">
                </div>
                <div>
                    <label for="difficulty">Difficulty</label>
                    
                    <label for="difficultyEASY" class="pl-5">EASY</label>
                    <input id="difficultyEASY" type="radio" value="EASY" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyNORMAL" class="pl-5">NORMAL</label>
                    <input id="difficultyNORMAL" type="radio" value="NORMAL" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyHARD" class="pl-5">HARD</label>
                    <input id="difficultyHARD" type="radio" value="HARD" v-model="difficulty" name="difficulty">
                    
                    <label for="difficultyIMPOSSIBLE" class="pl-5">IMPOSSIBLE</label>
                    <input id="difficultyIMPOSSIBLE" type="radio" value="IMPOSSIBLE" v-model="difficulty" name="difficulty">
                   
                    <label for="difficultyHOPELESS" class="pl-5">HOPELESS</label>
                    <input id="difficultyHOPELESS" type="radio" value="HOPELESS" v-model="difficulty" name="difficulty">
                </div>
            
                <div>
                    <label for="author">Author</label>
                    <input id="author" type="text" maxlength="8" v-model="author">
                </div>
                
                <button v-if="id" class="btn btn-info" v-on:click="addLabWork()" type="submit">Update</button>
                <button v-else class="btn btn-info" v-on:click="addLabWork()" type="submit">Add</button>
            </div>
            `,

        props: ["personslist", "coordinateslist"],
        data: function() {
            return {
                id: '',
                name: '',
                coordinates: '',
                minimalPoint: '',
                creationDate: '',
                difficulty: '',
                author: ''
            }
        },
        methods: {
            addLabWork: function () {
                let labWork = {
                    'name': this.name,
                    'coordinates': this.coordinates,
                    'creationDate': this.creationDate,
                    'minimalPoint': this.minimalPoint
                }
                if (this.difficulty) {
                    labWork.difficulty = this.difficulty
                }
                if (this.author) {
                    labWork.author = this.author
                }
                if (this.id) {
                    labWork.id = this.id
                }

                this.$emit('addlab', labWork)
                this.clearFields()
            },
            updateFields: function(labWork) {
                this.id = labWork.id
                this.name = labWork.name
                this.coordinates = labWork.coordinates.id
                this.creationDate = moment(labWork.creationDate, 'lll').format('YYYY-MM-DD')
                this.minimalPoint = labWork.minimalPoint
                this.difficulty = labWork.difficulty
                this.author = labWork.author.id
            },
            clearFields: function () {
                this.id = ""
                this.name = ""
                this.coordinates = ""
                this.minimalPoint = ""
                this.difficulty = ""
                this.author = ""
            }
        }
    }
);

Vue.component(
    'additional-actions',
    {
        template:
            `
            <div>
                <h1 class="col-xs-1 text-center">Additional actions</h1>
                <div>
                    <label for="minimalPoint">Count the number of objects which minimalPoint value is greater than:</label>
                    <input id="minimalPoint" type="text" maxlength="8" v-model="minimal_point">
                    <button class="btn btn-info" v-on:click="updateCalcByMinimalPoint()" type="submit">Count</button>
                </div>
                <div>{{ cnt }}</div>
                
                <div>
                    <label for="deleteByAuthor">Delete one (any) object which author name is:</label>
                    <input id="deleteByAuthor" type="text" maxlength="8" v-model="author_name">
                    <button class="btn btn-info" v-on:click="deleteByAuthor()" type="submit">Count</button>
                </div>
                <div>Id: {{deleted_by_author.id}} Name: {{deleted_by_author.name}}</div>
            </div>
            `,

        props: ["cnt", "deleted_by_author"],
        data: function() {
            return {
                minimal_point: '',
                author_name: ''
            }
        },
        methods: {
            updateCalcByMinimalPoint: function () {
                this.$emit('calcbyminimalpoint', this.minimal_point);
            },
            deleteByAuthor: function () {
                this.$emit('deletebyauthor', this.author_name);
            }
        }
    }
);