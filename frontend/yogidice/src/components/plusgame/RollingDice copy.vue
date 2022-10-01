<template>
    <div>
        <div id="webgl-container"></div>
    </div>
</template>

<script>
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';

export default {
    methods: {
        constructor() {
            const divContainer = document.querySelector("#webgl-container");
            this._divContainer = divContainer; // 다른 메서드에서 참조하기 위해 필드화로 할당해두는 과정

            const renderder = new THREE.WebGLRenderer({ antialias: true }); // antialias : object들을 매끄럾게 보여주는 
            renderder.setPixelRatio(window.devicePixelRatio); // 픽셀의 비율 => 윈도우의 픽셀비율속성으로 설정
            divContainer.appendChild(renderder.domElement); 
            this._renderer = renderder;

            const scene = new THREE.Scene();
            this._scene = scene; // scene 객체를 필드화시켜서 역시 다른 메서드에서 참조하도록 함

            this._setupCamera(); // camera에 대한 속성
            this._setupLight();   // Light에 대한 속성 
            this._setupModel();  // Mesh에 대한 속성

            this._setupControls();

            window.onresize = this.resize.bind(this); // 윈도우의 창 크기가 변경함에 따라 속성들을 재지정해줘야 하기 때문에 
            this.resize();

            requestAnimationFrame(this.render.bind(this)); // api에 넘겨주는 과정 => 호출하기 위함
        },
        _setupControls() {
            new OrbitControls(this._camera, this._divContainer);
        },

        _setupCamera() {
            const width = this._divContainer.clientWidth;
            const height = this._divContainer.clientHeight;
            const camera = new THREE.PerspectiveCamera(
                75, 
                width / height,
                0.1, 
                100
            );
            camera.position.z = 2;
            this._camera = camera;
        },

        _setupLight() {
            const color = 0xffffff;
            const intensity = 1;
            const light = new THREE.DirectionalLight(color, intensity);
            light.position.set(-1, 2, 4);
            this._scene.add(light);
        },

        _setupModel() {
            const loader = new THREE.TextureLoader();
                var materials = [
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/vkRprX3/dice-1.png")
                    }),
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/Br573sj/dice-2.png")
                    }),
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/mv6g4LZ/dice-3.png")
                    }),
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/dQYNFct/dice-4.png")
                    }),
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/9hnHZb7/dice-5.png")
                    }),
                    new THREE.MeshBasicMaterial({
                        map: loader.load("https://i.ibb.co/9N1nvkz/dice-6.png")
                    })
                ];

                const geometry = new THREE.BoxGeometry(1, 1, 1, 1, 1, 1, materials);

            const cube = new THREE.Mesh(geometry, materials);

            this._scene.add(cube);
            this._cube = cube;
        },

        resize() {
            const width = this._divContainer.clientWidth;
            const height = this._divContainer.clientHeight;

            this._camera.aspect = width / height;
            this._camera.updateProjectionMatrix();

            this._renderer.setSize(width, height);
        },

        render(time) {
            this._renderer.render(this._scene, this._camera);
            this.update(time);
            requestAnimationFrame(this.render.bind(this));
        },

        update(time) {
            time *= 0.0001;
            this._cube.rotation.x = time;
            this._cube.rotation.y = time;
            this._cube.rotation.z = time;
        }

    },
    mounted() {
        this.constructor()
    }
}
</script>